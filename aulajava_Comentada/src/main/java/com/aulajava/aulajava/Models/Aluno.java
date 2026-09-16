// Declaração do pacote - organiza as classes em uma estrutura hierárquica
package com.aulajava.aulajava.Models;

// Importações necessárias do JPA para mapeamento de entidades
import jakarta.persistence.Column; // Para definir propriedades das colunas
import jakarta.persistence.Entity; // Para marcar a classe como entidade do banco
import jakarta.persistence.Table;  // Para definir o nome da tabela

/**
 * CLASSE ALUNO - ENTIDADE FILHA DE PESSOA
 * 
 * Esta classe representa um aluno no sistema educacional.
 * Herda todos os atributos da classe Pessoa (nome, cpf, matricula)
 * e adiciona atributos específicos de um aluno.
 * 
 * CONCEITOS DE HERANÇA:
 * - extends Pessoa: significa que Aluno "é uma" Pessoa
 * - Herda automaticamente: nome, cpf, matricula
 * - Adiciona seus próprios atributos: nota
 * - Criará uma tabela "aluno" no banco de dados
 */

// @Entity: Marca esta classe como uma entidade JPA (criará uma tabela no banco)
@Entity
// @Table: Define o nome da tabela no banco de dados como "aluno"
@Table(name = "aluno")
public class Aluno extends Pessoa { // extends = herança da classe Pessoa

    /**
     * ATRIBUTO NOTA
     * Armazena a nota/média do aluno (específico da classe Aluno)
     * Este atributo NÃO existe na classe Pessoa, é exclusivo do Aluno
     */
    // @Column: Define as características da coluna "nota" no banco
    @Column(
        name = "nota",              // Nome da coluna será "nota"
        columnDefinition = "FLOAT", // Tipo de dado: número decimal (ex: 8.5, 9.2)
        nullable = false            // Campo obrigatório (todo aluno deve ter nota)
    )
    public float nota; // Tipo float para números decimais, variável pública

    /**
     * RESUMO DA CLASSE ALUNO:
     * 
     * ATRIBUTOS HERDADOS DE PESSOA:
     * - nome (String): nome do aluno
     * - cpf (String): CPF do aluno  
     * - matricula (Long): número de matrícula único
     * 
     * ATRIBUTOS PRÓPRIOS:
     * - nota (float): nota/média do aluno
     * 
     * TABELA NO BANCO:
     * - Nome da tabela: "aluno"
     * - Colunas: matricula, nome, cpf, nota
     * - Chave primária: matricula (herdada de Pessoa)
     * 
     * EXEMPLO DE USO:
     * Aluno aluno = new Aluno();
     * aluno.nome = "João Silva";
     * aluno.cpf = "12345678901";
     * aluno.nota = 8.5f;
     * // matricula será gerada automaticamente pelo banco
     */
}
