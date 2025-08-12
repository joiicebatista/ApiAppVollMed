package med.voll.api.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.AtualizarDadosMedico;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;
    private String celular;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Paciente(DadosCadastroPaciente dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.celular = dados.celular();
        this.email = dados.email();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(@Valid AtualizarDadosPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();

        }
        if (dados.celular() !=null){
            this.celular = dados.celular();
        }
        if (dados.endereco() != null){
            this.endereco.atualizarEndereco(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
