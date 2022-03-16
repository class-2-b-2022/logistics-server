module logistics {
	exports Services;
	exports models;
	exports Utils;
	exports models.DeliveryModule;
	exports Services.user_services;
	exports controllers;
	exports controllers.user_management;
	exports models.user_model;
	exports main;
	exports controllers.DeliveryModule;
	exports Services.DeliveryModule;
	exports Thread;

	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;
	requires java.sql;
	requires spring.security.crypto;
}