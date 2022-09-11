package com.nttdata.account.application.command.edit;

import io.jkratz.mediator.core.Command;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EditAccountCommand implements Command {

	private String accountNumber;

	private String accountType;

}
