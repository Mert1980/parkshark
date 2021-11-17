package com.switchfully.parkshark.api.controllers;


import com.switchfully.parkshark.dto.PersonDtoRequest;
import com.switchfully.parkshark.dto.PersonDtoResponse;
import com.switchfully.parkshark.services.PersonService;
import com.switchsecure.SecurityGuard;
import com.switchsecure.SecurityGuard.ApiUserRole;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class PersonController {
  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping(produces = "application/json")
  @ResponseStatus(value = HttpStatus.OK)
  public List<PersonDtoResponse> getAllMembers() {
    return personService.getAllMembers();
  }

  @GetMapping( path ="/{id}" , produces = "application/json")
  @ResponseStatus(value = HttpStatus.OK)
  public PersonDtoResponse getMemberById(@PathVariable long id) {
    return personService.getMemberById(id);
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public PersonDtoResponse registerMember(@RequestBody PersonDtoRequest personDtoRequest){
    return personService.registerMember(personDtoRequest);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public PersonDtoResponse deleteMember(long id) {
    return personService.deleteMember(id);
  }
}
