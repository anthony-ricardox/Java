// Declaração do pacote - define onde esta classe está localizada na estrutura do projeto
package com.aulajava.aulajava.Controller;

// ========== IMPORTAÇÕES JAVA PADRÃO ==========
import java.util.List; // Para trabalhar com listas de objetos
import java.util.Optional; // Para lidar com valores que podem ou não existir

// ========== IMPORTAÇÕES DO SPRING FRAMEWORK ==========
import org.springframework.beans.factory.annotation.Autowired; // Para injeção de dependência
import org.springframework.http.HttpStatus; // Para códigos de status HTTP (200, 404, etc)
import org.springframework.http.ResponseEntity; // Para respostas HTTP completas
import org.springframework.web.bind.annotation.RequestMapping; // Para mapear URLs base
import org.springframework.web.bind.annotation.RestController; // Para criar controlador REST
import org.springframework.web.bind.annotation.PostMapping; // Para requisições POST (criar)
import org.springframework.web.bind.annotation.RequestBody; // Para receber dados no corpo da requisição
import org.springframework.web.bind.annotation.DeleteMapping; // Para requisições DELETE (deletar)
import org.springframework.web.bind.annotation.GetMapping; // Para requisições GET (listar/buscar)
import org.springframework.web.bind.annotation.PathVariable; // Para receber parâmetros da URL
import org.springframework.web.bind.annotation.PutMapping; // Para requisições PUT (atualizar)

import com.aulajava.aulajava.Models.Aluno;
// ========== IMPORTAÇÕES DAS CLASSES DO PROJETO ==========
import com.aulajava.aulajava.Models.Professor; // Classe modelo Professor
import com.aulajava.aulajava.Repository.RepositoryAluno; // Repositório para operações com Aluno
import com.aulajava.aulajava.Repository.RepositoryProfessor; // Repositório para operações com Professor
import org.springframework.web.bind.annotation.RequestParam;


/**
 * CLASSE CONTROLLER - CONTROLADOR REST DA API
 * 
 * Esta é a classe principal que gerencia todas as requisições HTTP da
 * aplicação.
 * Ela funciona como uma ponte entre o cliente (frontend/Postman) e o banco de
 * dados.
 * 
 * CONCEITOS IMPORTANTES:
 * - REST API: Arquitetura para comunicação via HTTP usando métodos padrão
 * - Controller: Recebe requisições, processa e retorna respostas
 * - Endpoints: URLs específicas que executam operações diferentes
 * - CRUD: Create (POST), Read (GET), Update (PUT), Delete (DELETE)
 */

// @RestController: Marca esta classe como um controlador REST
// Significa que todos os métodos retornarão dados JSON automaticamente
@RestController

// @RequestMapping("/"): Define a URL base para todos os endpoints desta classe
// Neste caso, todos os endpoints começarão com "http://localhost:8080/"
@RequestMapping("/")
public class Controller {
    // ========== INJEÇÃO DE DEPENDÊNCIAS ==========
    /**
     * REPOSITÓRIOS - CAMADA DE ACESSO AOS DADOS
     * 
     * Os repositórios são responsáveis por fazer a comunicação com o banco de
     * dados.
     * Eles contêm métodos prontos para salvar, buscar, atualizar e deletar
     * registros.
     * 
     * @Autowired: O Spring cria automaticamente uma instância destes repositórios
     *             É como se o Spring fizesse: repositoryAluno = new
     *             RepositoryAluno();
     */
    @Autowired // Injeção automática de dependência pelo Spring
    RepositoryAluno repositoryAluno; // Para operações com a tabela "aluno"

    @Autowired // Injeção automática de dependência pelo Spring
    RepositoryProfessor repositoryProfessor; // Para operações com a tabela "professor"

    // ========== ENDPOINTS DA API REST ==========

    /**
     * ENDPOINT 1: CADASTRAR PROFESSOR
     * 
     * URL: POST http://localhost:8080/cadastrar-professor
     * Função: Criar um novo professor no banco de dados
     * 
     * COMO FUNCIONA:
     * 1. Cliente envia dados JSON no corpo da requisição
     * 2. Spring converte JSON para objeto Professor automaticamente
     * 3. Definimos matricula = null para forçar criação de novo registro
     * 4. Salvamos no banco usando o repositório
     * 5. Retornamos o professor salvo com status HTTP 201 (CREATED)
     */
    @PostMapping(value = "cadastrar-professor") // Mapeia requisições POST para esta URL
    public ResponseEntity<Professor> cadastrar(@RequestBody Professor professor) {
        // @RequestBody: Converte JSON recebido para objeto Professor

        // Limpa a matrícula para garantir que será um novo registro (não atualização)
        professor.matricula = null; // Acesso direto ao campo público

        // Salva o professor no banco de dados
        Professor resposta = repositoryProfessor.save(professor);

        // Retorna resposta HTTP com o professor salvo e status 201 (Created)
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    /**
     * ENDPOINT 2: LISTAR TODOS OS PROFESSORES
     * 
     * URL: GET http://localhost:8080/listar-professor
     * Função: Buscar todos os professores cadastrados no banco
     * 
     * COMO FUNCIONA:
     * 1. Cliente faz requisição GET (sem enviar dados)
     * 2. Buscamos todos os registros da tabela professor
     * 3. Spring converte automaticamente a lista para JSON
     * 4. Retorna lista de professores para o cliente
     */
    @GetMapping(value = "listar-professor") // Mapeia requisições GET para esta URL
    public List<Professor> listaProfessor() {
        // findAll() é um método pronto do JPA que busca todos os registros
        return repositoryProfessor.findAll();
    }

    /**
     * ENDPOINT 3: DELETAR PROFESSOR
     * 
     * URL: DELETE http://localhost:8080/deletar-professor/{matricula}
     * Função: Remover um professor específico do banco de dados
     * 
     * COMO FUNCIONA:
     * 1. Cliente envia matrícula na URL (ex: /deletar-professor/123)
     * 2. Verificamos se o professor existe no banco
     * 3. Se existe: deletamos e retornamos status 200 (OK)
     * 4. Se não existe: retornamos status 204 (NO_CONTENT)
     */
    @DeleteMapping("deletar-professor/{matricula}") // {matricula} é um parâmetro da URL
    public ResponseEntity<Long> deletarprofessor(@PathVariable Long matricula) {
        // @PathVariable: Extrai o valor {matricula} da URL

        // Verifica se existe um professor com esta matrícula
        boolean prof = repositoryProfessor.existsById(matricula);

        if (prof) {
            // Professor existe: deleta do banco
            repositoryProfessor.deleteById(matricula);
            return new ResponseEntity<>(matricula, HttpStatus.OK); // Status 200
        }
        // Professor não existe: retorna erro
        return new ResponseEntity<>(matricula, HttpStatus.NO_CONTENT); // Status 204
    }

    /**
     * ENDPOINT 4: ATUALIZAR PROFESSOR
     * 
     * URL: PUT http://localhost:8080/atualizar-professor/{matricula}
     * Função: Modificar dados de um professor existente
     * 
     * COMO FUNCIONA:
     * 1. Cliente envia matrícula na URL e novos dados no corpo JSON
     * 2. Buscamos o professor existente no banco
     * 3. Se existe: atualizamos os campos e salvamos
     * 4. Se não existe: retornamos erro 404 (NOT_FOUND)
     */
    @PutMapping("atualizar-professor/{matricula}") // Mapeia requisições PUT
    public ResponseEntity<Professor> atualizarprofessor(@PathVariable Long matricula,
            @RequestBody Professor professor) {
        // @PathVariable: matrícula da URL
        // @RequestBody: novos dados do professor em JSON

        // Optional: tipo que pode conter um valor ou estar vazio (evita
        // NullPointerException)
        Optional<Professor> existeProfessor = repositoryProfessor.findById(matricula);

        if (existeProfessor.isPresent()) {
            // Professor encontrado: atualiza os dados
            Professor atualizaProfessor = existeProfessor.get(); // Extrai o professor do Optional

            // Atualiza os campos usando acesso direto (sem getters/setters)
            atualizaProfessor.nome = professor.nome; // Atualiza nome
            atualizaProfessor.cpf = professor.cpf; // Atualiza CPF
            atualizaProfessor.disciplina = professor.disciplina; // Atualiza disciplina

            // Salva as alterações no banco
            repositoryProfessor.save(atualizaProfessor);
            return new ResponseEntity<>(atualizaProfessor, HttpStatus.OK); // Status 200
        }
        // Professor não encontrado: retorna erro
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Status 404
    }

    @PostMapping(value = "cadastrar-aluno")
    public ResponseEntity<Aluno> cadastrar(@RequestBody Aluno aluno) {
        aluno.matricula = null;
        Aluno salvo = repositoryAluno.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping(value = "listar-aluno")
    public List<Aluno> ListarAluno(){
         return repositoryAluno.findAll();
    }
    

    /**
     * RESUMO DOS ENDPOINTS CRIADOS:
     * 
     * 1. POST /cadastrar-professor - Criar novo professor
     * 2. GET /listar-professor - Listar todos os professores
     * 3. DELETE /deletar-professor/123 - Deletar professor por matrícula
     * 4. PUT /atualizar-professor/123 - Atualizar professor por matrícula
     * 
     * CÓDIGOS HTTP UTILIZADOS:
     * - 200 OK: Operação realizada com sucesso
     * - 201 CREATED: Novo registro criado com sucesso
     * - 204 NO_CONTENT: Recurso não encontrado para deletar
     * - 404 NOT_FOUND: Recurso não encontrado para atualizar
     * 
     * CONCEITOS IMPORTANTES:
     * - REST: Representational State Transfer (padrão de API)
     * - CRUD: Create, Read, Update, Delete (operações básicas)
     * - JSON: Formato de dados para comunicação
     * - HTTP Status: Códigos que indicam o resultado da operação
     */
}
