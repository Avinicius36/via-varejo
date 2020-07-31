package br.com.engdb.services.devportal.repositories.predicate;

import java.util.Optional;

import br.com.engdb.services.devportal.resources.ServiceDescriptionResource.ServiceDescriptionStatus;
import br.com.engdb.services.devportal.service.documents.QServiceDescription;

public class ServiceDescriptionPredicate extends BasePredicateBuilder {

	public ServiceDescriptionPredicate name(Optional<String> name) {
		if (name.isPresent()) {
			this.r().and(QServiceDescription.serviceDescription.name.containsIgnoreCase(name.get()));
		}
		return this;
	}

	public ServiceDescriptionPredicate category(Optional<String> categoryName) {
		if (categoryName.isPresent()) {
			this.r().and(QServiceDescription.serviceDescription.categories.any().name.equalsIgnoreCase(categoryName.get()));
		}
		return this;
	}

	public ServiceDescriptionPredicate environment(Optional<String> environmentName) {
		if (environmentName.isPresent()) {
			this.r().and(QServiceDescription.serviceDescription.environments.any().name.equalsIgnoreCase(environmentName.get()));
		}
		return this;
	}

	public ServiceDescriptionPredicate tag(Optional<String> tag) {
		if (tag.isPresent()) {
			this.r().and(QServiceDescription.serviceDescription.tags.any().eq(tag.get()));
		}
		return this;
	}

	public ServiceDescriptionPredicate status(Optional<ServiceDescriptionStatus> status) {
		if (status.isPresent()) {
			this.r().and(QServiceDescription.serviceDescription.status.eq(status.get()));
		}
		return this;
	}

	public ServiceDescriptionPredicate search(Optional<String> search) {
		if (search.isPresent()) {
			this.r().and(QServiceDescription.serviceDescription.name.containsIgnoreCase(search.get())
					.or(QServiceDescription.serviceDescription.description.containsIgnoreCase(search.get()))
					.or(QServiceDescription.serviceDescription.keywords.any().containsIgnoreCase(search.get()))
					.or(QServiceDescription.serviceDescription.tags.any().containsIgnoreCase(search.get()))
					.or(QServiceDescription.serviceDescription.categories.any().name.containsIgnoreCase(search.get()))
					.or(QServiceDescription.serviceDescription.environments.any().name.containsIgnoreCase(search.get()))
					.or(QServiceDescription.serviceDescription.owner.containsIgnoreCase(search.get())));
		}
		return this;
	}

}
