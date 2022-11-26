package com.cfg.bm.data.api.controller.api;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cfg.bm.data.api.model.User;
import com.cfg.bm.data.api.request.SearchRequest;

@RequestMapping("user")
@CrossOrigin
public interface UserController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    Page<User> findAll(@PageableDefault(page = 0, size = 10) Pageable pageable);

    @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    Page<User> search(SearchRequest searchRequest);

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    User findById(@Valid @PathVariable(name = "id", required = true) Long id);

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    User save(@Valid @RequestBody User user);

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    Void deleteById(@Valid @PathVariable(name = "id", required = true) Long id);
}
