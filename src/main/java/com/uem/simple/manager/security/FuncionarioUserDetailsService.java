package com.uem.simple.manager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.uem.simple.manager.model.Funcionario;
import com.uem.simple.manager.repository.FuncionarioRepository;

@Service
public class FuncionarioUserDetailsService implements UserDetailsService {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioUserDetailsService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String apelido) throws UsernameNotFoundException {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByApelido(apelido);
        Funcionario funcionario = funcionarioOptional.orElseThrow(() -> new UsernameNotFoundException("Apelido e/ou senha incorreto(s)"));
        return new User(apelido, funcionario.getSenha(), getPermissao(funcionario));
    }

    private Collection<? extends GrantedAuthority> getPermissao(Funcionario funcionario) {
        Set<SimpleGrantedAuthority> authority = new HashSet<>();
        authority.add(new SimpleGrantedAuthority(funcionario.getRole().toString().toUpperCase()));
        return authority;
    }
}
