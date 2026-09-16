// Declaração do pacote - organiza as classes em uma estrutura hierárquica
package com.aulajava.aulajava.Models;

// Importações necessárias do JPA para mapeamento de entidades
import jakarta.persistence.Column; // Para definir propriedades das colunas
import jakarta.persistence.Entity; // Para marcar a classe como entidade do banco
import jakarta.persistence.Table;  // Para definir o nome da tabela

/**
 * CLASSE PROFESSOR - ENTIDADE FILHA DE PESSOA
 * 
 * Esta classe representa um professor no sistema educacional.
 * Herda todos os atributos da classe Pessoa (nome, cpf, matricula)
 * e adiciona atributos específicos de um professor.
 * 
 * CONCEITOS DE HERANÇA:
 * - extends Pessoa: significa que Professor "é uma" Pessoa
 * - Herda automaticamente: nome, cpf, matricula
 * - Adiciona seus próprios atributos: disciplina
 * - Criará uma tabela "professor" no banco de dados
 */

// @Entity: Marca esta classe como uma entidade JPA (criará uma tabela no banco)
@Entity
// @Table: Define o nome da tabela no banco de dados como "professor"
@Table(name = "professor")
public class Professor extends Pessoa { // extends = herança da classe Pessoa

    /**
     * ATRIBUTO DISCIPLINA
     * Armazena a disciplina que o professor leciona (específico da classe Professor)
     * Este atributo NÃO existe na classe Pessoa, é exclusivo do Professor
     */
    // @Column: Define as características da coluna "disciplina" no banco
    @Column(
        name = "disciplina",              // Nome da coluna será "disciplina"
        columnDefinition = "VARCHAR(60)", // Tipo de dado: texto variável até 60 caracteres
        nullable = false                  // Campo obrigatório (todo professor deve ter disciplina)
    )
    public String disciplina; // Variável pública para acesso direto

    /**
     * RESUMO DA CLASSE PROFESSOR:
     * 
     * ATRIBUTOS HERDADOS DE PESSOA:
     * - nome (String): nome do professor
     * - cpf (String): CPF do professor  
     * - matricula (Long): número de matrícula único
     * 
     * ATRIBUTOS PRÓPRIOS:
     * - disciplina (String): matéria que o professor ensina
     * 
     * TABELA NO BANCO:
     * - Nome da tabela: "professor"
     * - Colunas: matricula, nome, cpf, disciplina
     * - Chave primária: matricula (herdada de Pessoa)
     * 
     * EXEMPLO DE USO:
     * Professor prof = new Professor();
     * prof.nome = "Maria Santos";
     * prof.cpf = "98765432100";
     * prof.disciplina = "Matemática";
     * // matricula será gerada automaticamente pelo banco
     */
}
