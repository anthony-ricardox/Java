// Declaração do pacote - organiza as classes em uma estrutura hierárquica
package com.aulajava.aulajava.Models;

// Importações necessárias do JPA (Java Persistence API) para mapeamento objeto-relacional
import jakarta.persistence.Column;        // Para definir propriedades das colunas no banco
import jakarta.persistence.GeneratedValue; // Para valores gerados automaticamente
import jakarta.persistence.GenerationType; // Para tipos de geração automática
import jakarta.persistence.Id;             // Para definir chave primária
import jakarta.persistence.MappedSuperclass; // Para classes de herança que não viram tabelas

/**
 * CLASSE PESSOA - SUPERCLASSE PARA HERANÇA
 * 
 * Esta é uma classe abstrata que serve como modelo base para outras entidades.
 * Ela contém os atributos comuns que serão herdados por Aluno e Professor.
 * 
 * CONCEITOS IMPORTANTES:
 * - Herança: Permite que outras classes (Aluno, Professor) herdem os atributos desta classe
 * - Encapsulamento: Os atributos são públicos para acesso direto (sem getters/setters)
 * - Mapeamento JPA: Define como os atributos Java se relacionam com colunas do banco de dados
 */

// @MappedSuperclass: Indica que esta classe é apenas para herança, NÃO criará uma tabela no banco
// Ou seja, apenas as classes filhas (Aluno, Professor) terão suas próprias tabelas
@MappedSuperclass
public class Pessoa {

    /**
     * ATRIBUTO NOME
     * Armazena o nome completo da pessoa (aluno ou professor)
     */
    // @Column: Define as características da coluna no banco de dados
    @Column(
        name = "nome",                    // Nome da coluna na tabela será "nome"
        columnDefinition = "VARCHAR(60)", // Tipo de dado: texto variável até 60 caracteres
        nullable = false                  // Campo obrigatório (não pode ser nulo)
    )
    public String nome; // Variável pública para acesso direto

    /**
     * ATRIBUTO CPF
     * Armazena o CPF da pessoa (documento de identificação brasileiro)
     */
    @Column(
        name = "cpf",                    // Nome da coluna será "cpf"
        columnDefinition = "VARCHAR(11)" // Texto de até 11 caracteres (apenas números do CPF)
        // nullable não especificado = pode ser nulo (opcional)
    )
    public String cpf; // Variável pública para acesso direto

    /**
     * ATRIBUTO MATRÍCULA - CHAVE PRIMÁRIA
     * Número único que identifica cada pessoa no sistema
     * É a chave primária da tabela (cada registro tem um valor único)
     */
    @Id // Define este campo como chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Valor gerado automaticamente pelo banco
    // IDENTITY = o banco de dados gera números sequenciais automaticamente (1, 2, 3, 4...)
    @Column(name = "matricula") // Nome da coluna será "matricula"
    public Long matricula; // Tipo Long para números grandes, variável pública

    /**
     * RESUMO DA CLASSE:
     * 
     * 1. Esta classe define a estrutura básica de uma pessoa
     * 2. Não cria tabela própria, apenas serve de modelo para herança
     * 3. Contém 3 atributos essenciais: nome, cpf e matrícula
     * 4. A matrícula é gerada automaticamente pelo banco de dados
     * 5. Todas as variáveis são públicas para acesso direto
     * 6. As anotações JPA fazem o mapeamento entre Java e banco de dados
     */
}
