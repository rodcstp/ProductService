package br.com.test.product.rest.controllers.base;

import br.com.test.product.rest.services.base.IBaseBO;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;

public abstract class BController<BO extends IBaseBO<?, DTO>, DTO> {
	@Getter(AccessLevel.PROTECTED)
	private		final		BO		service;

	public BController(BO service) {
		this.service 	= service;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<?> findOne(@PathVariable("id") UUID id, @AuthenticationPrincipal UserDetails details) {
		return ResponseEntity.ok(service.findOne(id, details));
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll(@AuthenticationPrincipal UserDetails details) {
		return ResponseEntity.ok(service.findAll(details));
	}

	@RequestMapping(method = RequestMethod.GET, path = "/page")
	public ResponseEntity<?> findAll(Pageable pageable, @AuthenticationPrincipal UserDetails details) {
		return ResponseEntity.ok(service.findAll(pageable, details));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DTO> save(@RequestBody @Valid DTO dto, @AuthenticationPrincipal UserDetails account) {
		return ResponseEntity.ok(service.save(dto, account));
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DTO> update(@RequestBody @Valid DTO dto, @AuthenticationPrincipal UserDetails account) {
		return ResponseEntity.ok(service.save(dto, account));
	}

	@RequestMapping(method = RequestMethod.POST, path = "/collection", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DTO>> save(@RequestBody @Valid Collection<DTO> collection, @AuthenticationPrincipal UserDetails account) {
		return ResponseEntity.ok(service.save(collection, account));
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/collection", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DTO>> update(@RequestBody @Valid Collection<DTO> collection, @AuthenticationPrincipal UserDetails account) {
		return ResponseEntity.ok(service.save(collection, account));
	}
}