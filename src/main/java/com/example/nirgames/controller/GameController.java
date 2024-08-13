package com.example.nirgames.controller;

import com.example.nirgames.dto.GameDto;
import com.example.nirgames.mapper.GameMapper;
import com.example.nirgames.model.Customer;
import com.example.nirgames.model.Game;
import com.example.nirgames.service.CustomerService;
import com.example.nirgames.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;
    private final CustomerService customerService;
    private final GameMapper gameMapper;

    @GetMapping
    public Page<GameDto> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);

        var allGame = gameService.findAll(paging);
        Page<GameDto> dtoPage = allGame.map(x -> gameMapper.map(x));

        return dtoPage;
    }

    @GetMapping("/withoutPaging")
    public ResponseEntity<List<GameDto>> findAll() {
        var allGame = gameService.findAll();
        List<GameDto> dtoPage = allGame.stream().map(gameMapper::map).toList();

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> findByTitle(@PathVariable("id") Long id) {
        Game gameByTitle = gameService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("что то пошло не так." +
                        " возможно такой игры мы еще не знаем." +
                        " вы можете добавить ее к нам в коллекцию)"));
        GameDto gameDto = gameMapper.map(gameByTitle);
        return ResponseEntity.ok(gameDto);
    }

    @GetMapping("/all-by-customer")
    public Page<GameDto> getAllByCustomer(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "3") int size,
                                          @RequestParam() Long customerId) {
        Pageable paging = PageRequest.of(page, size);
        var allGame = gameService.findByUser(customerService.findById(customerId).get(), paging);
        Page<GameDto> dtoPage = allGame.map(x -> gameMapper.map(x));

        return dtoPage;
    }

    @GetMapping("/score")
    public ResponseEntity<Integer> getScore(@RequestParam Long id){
        Integer scoreByGameId = gameService.getScoreByGameId(id);
        scoreByGameId = scoreByGameId == null? 0: scoreByGameId;
        return ResponseEntity.ok(scoreByGameId);
    }
    @PostMapping()
    public ResponseEntity<GameDto> save(@RequestBody GameDto dto) {
        Game save = gameService.save(gameMapper.map(dto));
        return ResponseEntity.ok(gameMapper.map(save));
    }

    @PutMapping()
    public ResponseEntity<GameDto> update(@RequestBody GameDto dto) {
        return null; //todo
    }
}
