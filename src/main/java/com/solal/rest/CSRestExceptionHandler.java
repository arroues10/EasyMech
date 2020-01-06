package com.solal.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.solal.rest.controller.LoginController;
import com.solal.rest.controller.ManagerController;
import com.solal.rest.controller.MechanicController;
import com.solal.rest.controller.StoreKeeperController;
import com.solal.rest.ex.InvalidLoginException;
import com.solal.rest.ex.InvalidTokenException;
import com.solal.rest.ex.WorkCardNotExistsException;

/**
 * This class was created to receive all the exceptions of the project to make
 * them readable
 * 
 * @author Solal Arroues
 *
 */
@ControllerAdvice(assignableTypes = { ManagerController.class, StoreKeeperController.class, MechanicController.class,
		LoginController.class })
public class CSRestExceptionHandler {

	@ExceptionHandler(InvalidTokenException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public CSErrorResponse handleInvalidToken(InvalidTokenException ex) {
		return CSErrorResponse.now(HttpStatus.UNAUTHORIZED, String.format("Unauthorized %s", ex.getMessage()));
	}

	@ExceptionHandler(InvalidLoginException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public CSErrorResponse handleInvalidLogin(InvalidLoginException ex) {
		return CSErrorResponse.now(HttpStatus.UNAUTHORIZED, String.format("Unauthorized %s", ex.getMessage()));
	}

	@ExceptionHandler(WorkCardNotExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public CSErrorResponse handleNoSuchMember(WorkCardNotExistsException ex) {
		return CSErrorResponse.now(HttpStatus.BAD_REQUEST, String.format("Bad request %s", ex.getMessage()));
	}
//
//	@ExceptionHandler(NoSuchCouponException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ResponseBody
//	public CSErrorResponse handleNoSuchCoupon(NoSuchCouponException ex) {
//		return CSErrorResponse.now(HttpStatus.BAD_REQUEST, String.format("Bad request %s", ex.getMessage()));
//	}
//
//	@ExceptionHandler(CouponAlreadyPurchasedException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ResponseBody
//	public CSErrorResponse handleCouponAlreadyPurchased(CouponAlreadyPurchasedException ex) {
//		return CSErrorResponse.now(HttpStatus.BAD_REQUEST, String.format("Bad request %s", ex.getMessage()));
//	}
//
//	@ExceptionHandler(CustomerAlreadyExistsException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ResponseBody
//	public CSErrorResponse handleCustomerAlreadyExists(CustomerAlreadyExistsException ex) {
//		return CSErrorResponse.now(HttpStatus.BAD_REQUEST, String.format("Bad request %s", ex.getMessage()));
//	}
//
//	@ExceptionHandler(CompanyAlreadyExistsException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ResponseBody
//	public CSErrorResponse handleCompanyAlreadyExists(CompanyAlreadyExistsException ex) {
//		return CSErrorResponse.now(HttpStatus.BAD_REQUEST, String.format("Bad request %s", ex.getMessage()));
//	}
//
//	@ExceptionHandler(ZeroCouponAmountException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ResponseBody
//	public CSErrorResponse handleZeroCouponAmount(ZeroCouponAmountException ex) {
//		return CSErrorResponse.now(HttpStatus.BAD_REQUEST, String.format("Bad request %s", ex.getMessage()));
//	}
//
//	@ExceptionHandler(NonExistingCategoryException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ResponseBody
//	public CSErrorResponse handleNonExistingCategory(NonExistingCategoryException ex) {
//		return CSErrorResponse.now(HttpStatus.BAD_REQUEST, String.format("Bad request %s", ex.getMessage()));
//	}
//
//	@ExceptionHandler(InvalidPriceException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ResponseBody
//	public CSErrorResponse handleInvalidPrice(InvalidPriceException ex) {
//		return CSErrorResponse.now(HttpStatus.BAD_REQUEST, String.format("Bad request %s", ex.getMessage()));
//	}
}
