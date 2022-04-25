package com.coinsucks.core.wallet.dto.input;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class WalletInputDto {
    @NotBlank
    private final String name;
    private final String desc;
}
