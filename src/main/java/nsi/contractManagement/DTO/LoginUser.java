package nsi.contractManagement.DTO;

import lombok.Data;
import nsi.contractManagement.DO.UserDO;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @Author: Tao
 * @Time: 2020/10/11 9:47
 * @ProjectName: contract-management
 * @FileName: LoginUser.java
 * @IDE: IntelliJ IDEA
 */
@Data
public class LoginUser implements UserDetails, CredentialsContainer {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    /**
     * 用户
     */
    private UserDO user;
    /**
     * 登录ip
     */
    private String loginIp;
    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    public LoginUser() {
    }

    public LoginUser(UserDO user, String loginIp, LocalDateTime loginTime) {
        this.user = user;
        this.loginIp = loginIp;
        this.loginTime = loginTime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * 账户是否未过期，过期无法验证
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁，锁定的用户无法进行身份验证
     * <p>
     * 密码锁定
     * </p>
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码)，过期的凭据防止认证
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否被启用或禁用。禁用的用户无法进行身份验证。
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 认证完成后，擦除密码
     */
    @Override
    public void eraseCredentials() {
        user.setPassword(null);
    }
}
