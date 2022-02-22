package com.cfg.bm.data.api.controller.entrypoint;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class DefaultEntryPoint {

	@GetMapping("/")
	@ResponseStatus(code = HttpStatus.OK)
	public void defaultEntryPoint() {

	}
}
