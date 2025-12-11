package com.person.person.services;

import com.person.person.dto.PersonDepDTO;
import com.person.person.entities.Department;
import com.person.person.entities.Person;
import com.person.person.repositories.DepartmentRepository;
import com.person.person.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private DepartmentRepository departmentRepository;


    @Transactional
    public PersonDepDTO findById(Long id){
        //buscou no banco de dados oque recebel com argumento o ID
        //O repositpry busca no banco de dados o id que chegou como argumento

        Person person = repository.findById(id).orElseThrow(() -> new ExpressionException("Recurso não encontrado"));
        return new PersonDepDTO(person);
    }

    @Transactional
    public PersonDepDTO insert(PersonDepDTO dto){
        Person entity = new Person();
        entity.setName(dto.getName());
        entity.setSalary(dto.getSalary());

        /// não retorna o departamento no postman
        //Department dep = new Department();
        //dep.setId(dto.getDepartment().getId());

        /// retorna o departamento no postman
        Department dep = departmentRepository.getReferenceById(dto.getDepartment().getId());

        entity.setDepartment(dep);
        entity = repository.save(entity);

        return new PersonDepDTO(entity);
    }

}
