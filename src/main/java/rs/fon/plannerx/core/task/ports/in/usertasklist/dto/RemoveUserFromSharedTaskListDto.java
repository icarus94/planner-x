package rs.fon.plannerx.core.task.ports.in.usertasklist.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class RemoveUserFromSharedTaskListDto extends SelfValidating<RemoveUserFromSharedTaskListDto> {
    @NotNull
    @Digits(fraction = 0, integer = 12)
    int invokerUserId;

    @NotNull
    @Digits(fraction = 0, integer = 12)
    int targetUserId;

    @NotNull
    @Digits(fraction = 0, integer = 12)
    int targetTaskListId;

    public RemoveUserFromSharedTaskListDto(int invokerUserId, int targetUserId, int targetTaskListId) {
        this.invokerUserId = invokerUserId;
        this.targetUserId = targetUserId;
        this.targetTaskListId = targetTaskListId;
        this.validateSelf();
    }
}
