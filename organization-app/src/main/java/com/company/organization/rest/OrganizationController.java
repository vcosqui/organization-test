package com.company.organization.rest;

import com.company.organization.domain.Organization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.company.organization.rest.HierarchyRepresentation.downstreamHierarchy;
import static com.company.organization.rest.HierarchyRepresentation.upstreamHierarchy;

@RestController
public class OrganizationController {

    private final Organization organization;

    public OrganizationController(final Organization organization) {
        this.organization = organization;
    }

    @PostMapping(value = "/organization", consumes = "application/json")
    public void setOrganization(@RequestBody final Map<String, String> organizationReceived) {
        organization.addEmployees(organizationReceived);
    }

    @GetMapping(value = "/organization", produces = "application/json")
    public Map<String, Map> getOrganization() {
        return downstreamHierarchy(organization.getRoot().map(List::of).orElse(List.of()));
    }

    @GetMapping(value = "/organization/employee/{employeeName}/management", produces = "application/json")
    public Map<String, Map> getEmployee(@PathVariable(value = "employeeName") String employeeName) {
        // todo.map(::upstreamHierarchy);
        return upstreamHierarchy(organization.getEmployee(employeeName).get());
    }
}
