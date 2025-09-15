package com.eventhall.dto.request;


import java.time.Instant;
import java.util.UUID;

import com.eventhall.entities.Companies;
import com.eventhall.entities.enums.ContactTypeEnum;
import com.eventhall.entities.enums.DocumentTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CompaniesDTO {
	public UUID id;
	public String name;
	public DocumentTypeEnum documentType;
	public String document;
	public String contact;
	public ContactTypeEnum contactType;
	public String slug;
	public Boolean termsOfUser;
	public Boolean paymentActive;
	public String createdByUser;
	public Instant activedAt;
	
	
	public CompaniesDTO(Companies company) {
	this.id = company.getId(); 
	this.name = company.getName(); 
	this.documentType = company.getDocumentType(); 
	this.document = company.getDocument(); 
	this.contact = company.getContact(); 
	this.contactType = company.getContactType(); 
	this.slug = company.getSlug(); 
	this.termsOfUser = company.getTermsOfUser(); 
	this.paymentActive = company.getPaymentActive(); 
	this.createdByUser = company.getCreatedByUser(); 
	this.activedAt = company.getActivedAt(); }
}