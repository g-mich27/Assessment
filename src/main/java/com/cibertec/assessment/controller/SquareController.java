package com.cibertec.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.assessment.beans.SquareBean;
import com.cibertec.assessment.model.Square;
import com.cibertec.assessment.service.SquareService;

@RestController
@RequestMapping("/square")
public class SquareController {

	@Autowired
    private SquareService squareService;

	@GetMapping
    public ResponseEntity <List<SquareBean>> getAllSquares() {
    	List<SquareBean> squares = squareService.list();
    	return new ResponseEntity<>(squares, HttpStatus.OK);
    }
	
    @PostMapping
    public ResponseEntity<?> createSquare(@RequestBody Square s) {
        squareService.create(s);
		return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Square> update(@RequestBody Square s) {
    	return new ResponseEntity<>(squareService.update(s), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
    	squareService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
