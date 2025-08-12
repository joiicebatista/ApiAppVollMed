package med.voll.api.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosEndereco;

public record AtualizarDadosPaciente(
        @NotNull
        Long id,
        String nome,
        String celular,
        DadosEndereco endereco
) {
}
