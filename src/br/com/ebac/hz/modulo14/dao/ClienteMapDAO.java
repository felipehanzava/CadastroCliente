package br.com.ebac.hz.modulo14.dao;

import br.com.ebac.hz.modulo14.domain.Cliente;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClienteMapDAO implements IClienteDAO{

    private Map<Long, Cliente> map;

    public ClienteMapDAO(){
        this.map = new HashMap<>();
    }

    @Override
    public Boolean cadastrar(Cliente cliente) {
        if (this.map.containsKey(cliente.getCpf())){
            return false;
        }
        this.map.put(cliente.getCpf(), cliente);
        return true;
    }

    @Override
    public void excluir(Long cpf) {
        Cliente clienteCadastrado = this.map.get(cpf);

        if (clienteCadastrado != null){
            this.map.remove(clienteCadastrado.getCpf(), clienteCadastrado);
        }
    }

    @Override
    public void alterar(Cliente cliente) {
        Cliente clienteCadastado = this.map.get(cliente.getCpf());
        if(clienteCadastado != null){
            clienteCadastado.setNome(cliente.getNome());
            clienteCadastado.setTel(cliente.getTel());
            clienteCadastado.setEnd(cliente.getEnd());
            clienteCadastado.setNumero(cliente.getNumero());
            clienteCadastado.setCidade(cliente.getCidade());
            clienteCadastado.setEstado(cliente.getEstado());
        }
    }

    @Override
    public Cliente consultar(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.map.values();
    }
}
