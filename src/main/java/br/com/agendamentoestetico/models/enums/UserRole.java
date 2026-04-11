package br.com.agendamentoestetico.models.enums;

public enum UserRole {
    ROLE_ADMIN("admin"),
    ROLE_GERENTE("gerente"),
    ROLE_FUNCIONARIO("funcionario");

    private String role;
    UserRole(String role){ this.role = role; }
    public String getRole(){ return role; }
}