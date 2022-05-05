package com.alkemy.proyecto.alkemy.services;


import java.util.List;
import java.util.stream.Collectors;

import com.alkemy.proyecto.alkemy.entities.Usuario;
import com.alkemy.proyecto.alkemy.repositories.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository repoUsuario;

    @Override
    @Transactional(readOnly= true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = repoUsuario.findByUsername(username);

        if(usuario == null) {
            logger.error("No existe el usuario  en el sistema");
            throw new  UsernameNotFoundException("No existe el usuario  en el sistema");
        }

        List<GrantedAuthority> authorities = usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
        
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
    }
    
}
