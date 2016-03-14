package com.frank.utils.redis.config;

import com.frank.utils.string.EmptyUtils;

/**
 * @author <a href="mailto:heshanshao@ebnew.com">Administrator</a>
 * @version V1.0
 * @date 2016/3/11
 */
public class RedisContext {

    /**
     * 记录租户标识
     */
    private final static InheritableThreadLocal<String> TENANT_LOCAL = new InheritableThreadLocal<String>();

    /**
     * 获取租户标识.
     *
     * @return
     */
    public static String getTenant() {
        return EmptyUtils.isEmpty(TENANT_LOCAL.get()) ? "" : TENANT_LOCAL.get().trim() + "_";
    }

    /**
     * 设置租户标识.
     *
     * @param tenant
     */
    public static void setTenant(String tenant) {
        if (!EmptyUtils.isEmpty(tenant)) {
            TENANT_LOCAL.set(tenant);
        }
    }

    /**
     * 删除.
     *
     */
    public static void remove() {
        TENANT_LOCAL.remove();
    }
}
