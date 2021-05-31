package cc.mrbird.febs.common.handler;

import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.exception.FileDownloadException;
import cc.mrbird.febs.common.exception.LimitAccessException;
import cc.mrbird.febs.common.exception.LimitAccessViewException;
import cc.mrbird.febs.common.i18n.MessageUtils;
import cc.mrbird.febs.common.utils.FebsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public FebsResponse handleException(Exception e) {
        e.printStackTrace();
        log.error("系统内部异常，异常信息 {}", e.getMessage());
        return new FebsResponse().code(HttpStatus.INTERNAL_SERVER_ERROR).message(MessageUtils.getMessage("globalHandler.system.error"));
    }

    @ExceptionHandler(value = FebsException.class)
    public FebsResponse handleFebsException(FebsException e) {
        log.error("系统错误 {}", e.getMessage());
        return new FebsResponse().code(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getMessage());
    }

    @ExceptionHandler(value = LimitAccessException.class)
    public FebsResponse handleLimitAccessApiException(LimitAccessException e) {
        log.error("LimitAccessException, {}", e.getMessage());
        return new FebsResponse().code(HttpStatus.BAD_REQUEST).message(e.getMessage());
    }

    @ExceptionHandler(value = LimitAccessViewException.class)
    public Object handleLimitAccessViewException(LimitAccessViewException e) {
        log.error("666 handleLimitAccessViewException, {}", e.getMessage());

        ModelAndView mav = new ModelAndView();
        mav.setViewName(FebsUtil.view("error/429"));
//        mav.setViewName("error/429");
        return mav;
    }


    /**
     * 处理我们调用俄罗斯接口时 关于金额的异常
     * @param e
     * @return
     */
    /*@ExceptionHandler(value = FmException.class)
    public ApiResponse handleFmException(FmException e) {
        log.error("RCS 金额异常 {} ", e.getMessage());
        return new ApiResponse(HttpStatus.BAD_REQUEST.value(),
                new OperationError(HttpStatus.BAD_REQUEST.value(),e.getMessage(),e.getManagerBalanceDTO()));
    }*/


    @ExceptionHandler(value = UnauthorizedException.class)
    public FebsResponse handleUnauthorizedException(UnauthorizedException e) {
        log.error("UnauthorizedException, {}", e.getMessage());
        return new FebsResponse().code(HttpStatus.FORBIDDEN).message(e.getMessage());
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public FebsResponse handleAuthenticationException(AuthenticationException e) {
        log.error("AuthenticationException, {}", e.getMessage());
        return new FebsResponse().code(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getMessage());
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public FebsResponse handleAuthorizationException(AuthorizationException e){
        log.error("AuthorizationException, {}", e.getMessage());
        return new FebsResponse().code(HttpStatus.UNAUTHORIZED).message(e.getMessage());
    }


    @ExceptionHandler(value = ExpiredSessionException.class)
    public FebsResponse handleExpiredSessionException(ExpiredSessionException e) {
        log.error("ExpiredSessionException", e);
        return new FebsResponse().code(HttpStatus.UNAUTHORIZED).message(e.getMessage());
    }

    @ExceptionHandler(value = FileDownloadException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleFileDownloadException(FileDownloadException e) {
        log.error("FileDownloadException", e);
    }
}
