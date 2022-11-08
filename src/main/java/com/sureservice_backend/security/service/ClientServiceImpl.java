package com.sureservice_backend.security.service;

import com.sureservice_backend.security.domain.model.entity.Client;
import com.sureservice_backend.security.domain.model.entity.Role;
import com.sureservice_backend.security.domain.persistence.ClientRepository;
import com.sureservice_backend.security.domain.persistence.RoleRepository;
import com.sureservice_backend.security.domain.persistence.UserRepository;
import com.sureservice_backend.security.domain.service.ClientService;
import com.sureservice_backend.security.domain.service.communication.*;
import com.sureservice_backend.security.mapping.ClientMapper;
import com.sureservice_backend.shared.exception.ResourceNotFoundException;
import com.sureservice_backend.shared.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    ClientMapper mapper;


    @Override
    public Client register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new ResourceValidationException("Username is already used.");

        if (userRepository.existsByEmail(request.getEmail()))
            throw new ResourceValidationException("Email is already used.");

        Role role = roleRepository.findAllById(1L);

        if(role == null)
            throw new ResourceValidationException("Role not found");

        try{
            Client client = new Client();
            client.setAmount_reservation(0);
            client.setUsername(request.getUsername());
            client.setEmail(request.getEmail());
            client.setPassword(encoder.encode(request.getPassword()));
            client.setRol(role);
            client.setDni(request.getDni());
            client.setTelephone_number(request.getTelephone_number());
            client.setName(request.getName());
            client.setLast_name(request.getLast_name());
            client.setImage_url("https://res.cloudinary.com/daslzhbab/image/upload/v1667853785/mmb0zluthi93wazo6vaa.jpg");

            return clientRepository.save(client);
        } catch (Exception e){
            throw new ResourceValidationException(e.getMessage());
        }
    }

    @Override
    public Client update(Long clientId, UpdateClientRequest request) {

        Role role = roleRepository.findAllById(1L);



        Client client = clientRepository.getById(clientId);
        client.setId(clientId);
        client.setAmount_reservation(request.getAmount_reservation());
        client.setUsername(request.getUsername());
        client.setEmail(request.getEmail());
        client.setRol(role);
        client.setDni(request.getDni());
        client.setTelephone_number(request.getTelephone_number());
        client.setName(request.getName());
        client.setLast_name(request.getLast_name());

        return clientRepository.findById(clientId).map( data->
            clientRepository.save(client)
        ).orElseThrow(()-> new ResourceNotFoundException("Client", clientId));
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
    }
}
