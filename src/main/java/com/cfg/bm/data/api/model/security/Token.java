package com.cfg.bm.data.api.model.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token {

    String type;
    String token;
}
