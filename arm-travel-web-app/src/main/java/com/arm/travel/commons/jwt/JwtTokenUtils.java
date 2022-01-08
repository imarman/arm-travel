package com.arm.travel.commons.jwt;

import com.arm.travel.commons.utils.json.FastJsonUtil;
import com.arm.travel.pojo.SysUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtTokenUtils {

    // 携带token的请求头名字
    public static final String TOKEN_HEADER = "Authorization";

    //token的前缀
    public static final String TOKEN_PREFIX = "Bearer ";

    // 默认密钥
    public static final String DEFAULT_SECRET = "abcdefghijklmnoqprstuvwxyz";

    // 一分钟
    public static final long ONE_MINUTES = 60 * 1000;

    // App 30 分钟
    public static final long TOKEN_EXPIRATION = 30 * ONE_MINUTES;

    // 续期时间 是 token 是两倍
    public static final long REDIS_TOKEN_EXPIRATION = 2 * TOKEN_EXPIRATION;

    // 具体加密的算法
    private static final Algorithm algorithm = Algorithm.HMAC256(DEFAULT_SECRET);


    /**
     * SysLoginUser 登录用户
     *
     * @return 生成的 token
     */
    public static String createToken(SysUser sysLoginUser) {
        // 2：创建token
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("typ", "ZIP");
        return JWT.create()
                // 放进去的目的，方便你获取用户信息，避免查询,最后是去掉，
                // 后续可能会根据id去查询数据，实时的获取用户信息
                .withClaim("token", FastJsonUtil.toJSON(sysLoginUser))
                .withHeader(headerClaims)
                // 放一份在主题里
                .withSubject(sysLoginUser.getId() + "")
                // 签发 token 作者
                .withIssuer(sysLoginUser.getUsername())
                // 设置签发时间：iat
                .withIssuedAt(generateCurrentDate())
                // 设置过期时间：exp，必须要大于签发时间
                .withExpiresAt(generateExpirationDate())
                // 签名信息，采用secret作为私钥
                .sign(algorithm);
    }

    /**
     * 检验token是否正确
     *
     * @param token
     * @return
     */
    // public static SysUser parseToken(String token) {
    //     String tokenInfo = getJWTFromToken(token).getClaim("token").asString();
    //     return FastJsonUtil.toBean(tokenInfo, SysUser.class);
    // }

    /**
     * 检验token是否正确
     *
     * @param token token
     * @return 解析后的内容
     */
    public static String parseToken(String token) {
        DecodedJWT jwt = getJWTFromToken(token);
        if (jwt != null) {
            return jwt.getSubject();
        }
        return null;
    }


    /**
     * 验证是否token过期
     *
     * @param token token
     * @return true 表示没过期
     */
    public static boolean isVerify(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            // 如果token失效了，verify就会出现异常
            verifier.verify(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean checkToken(HttpServletRequest request) {
        String token = getJwtToken(request);
        return isVerify(token);
    }


    /**
     * @param token
     * @return
     */
    public static DecodedJWT getJWTFromToken(String token) {
        DecodedJWT jwt;
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            jwt = verifier.verify(token);
        } catch (Exception ex) {
            jwt = null;
            log.info("token is expired....");
        }
        return jwt;
    }


    /**
     * 从请求头中获取token
     *
     * @param request 请求头
     * @return token
     */
    public static String getJwtToken(HttpServletRequest request) {
        // 如果cookie中没有，就去header里面获取
        String header = request.getHeader(TOKEN_HEADER);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            log.info("请求头不含JWT token， 调用下个过滤器");
            return null;
        }
        // 去掉 token 的 prefix
        return header.split(" ")[1].trim();
    }


    /**
     * 当前时间
     *
     * @return 当前时间
     */
    private static Date generateCurrentDate() {
        return new Date();
    }


    /**
     * 过期时间
     *
     * @return 过期时间
     */
    private static Date generateExpirationDate() {
        return new Date(DateTime.now().getMillis() + TOKEN_EXPIRATION);
    }

}
