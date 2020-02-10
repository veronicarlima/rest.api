package com.ramires.rest.api.resource;

import com.ramires.rest.api.model.Categoria;
import com.ramires.rest.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CartegoriaResource {

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping
    public  List<Categoria> listar(){
        return categoriaRepository.findAll();
    }

//    @GetMapping   **Aqui a gente envia uma entidade de resposta e responde com ela. Sendo o de baixo um status 204 não encontrada
//    public ResponseEntity<?>  listar2(){
//        List<Categoria> categorias = categoriaRepository.findAll();
//        return !categorias.isEmpty() ? ResponseEntity.ok(categorias) : ResponseEntity.noContent().build();
//    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criar(@RequestBody Categoria categoria, HttpServletResponse response){
        Categoria incluirCategoria = categoriaRepository.save(categoria);

        //Esse metodo serve para poder assim que der OK no post aparecer o caminho onde foi salvo para recuperar
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
                .buildAndExpand(incluirCategoria.getCodigo()).toUri();

        response.setHeader("Location", uri.toASCIIString());
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)   Enviando com o ResponseEntity na resposta podemos trazer os dados da inclusão no Body da resposta
//    public ResponseEntity<Categoria> criar2(@RequestBody Categoria categoria, HttpServletResponse response){
//        Categoria incluirCategoria = categoriaRepository.save(categoria);
//
//        //Esse metodo serve para poder assim que der OK no post aparecer o caminho onde foi salvo para recuperar
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
//                .buildAndExpand(incluirCategoria.getCodigo()).toUri();
//        response.setHeader("Location", uri.toASCIIString());
//
//        return ResponseEntity.created(uri).body(incluirCategoria);
//    }

//    @GetMapping("/{codigo}") Buscar o código criado ****
//    public Categoria buscarPeloCodigo(@PathVariable Long codigo){
//        return categoriaRepository.findOne(codigo);
//    }



}
