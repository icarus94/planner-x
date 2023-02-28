package rs.fon.plannerx.core.account.ports.in.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Value
@EqualsAndHashCode(callSuper = false)
public class PaginationDto extends SelfValidating<PaginationDto> {
    @Digits(fraction = 0, integer = 12)
    @PositiveOrZero
    int page;

    @Digits(fraction = 0, integer = 12)
    @PositiveOrZero
    int pageSize;

    @NotNull
    @NotBlank
    String sortBy;

    @NotNull
    @NotBlank
    String sortDirection;

    public PaginationDto(int page, int pageSize, String sortBy, String sortDirection) {
        this.page = page;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
        this.validateSelf();
    }
}
