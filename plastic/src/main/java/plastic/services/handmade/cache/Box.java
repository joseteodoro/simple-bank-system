package plastic.services.handmade.cache;

import java.lang.ref.SoftReference;

import plastic.models.Brand;

public class Box {

    private final long createdAt;

    private final String key;

    private final SoftReference<Brand> softReference;

    private long usageCount;

    private long lastUsage;
    
    private Box(String key, Brand value) {
        this.softReference = new SoftReference<>(value);
        this.createdAt = CacheUtils.toEpoch();
        this.usageCount = 0;
        this.lastUsage = this.createdAt;
        this.key = key;
    }

    public static Box of(String key, Brand value) {
        return new Box(key, value);
    }

    public synchronized Brand use() {
        this.lastUsage = CacheUtils.toEpoch();
        this.usageCount++;
        return this.softReference.get();
    }

    public Brand getValue() {
        return this.softReference.get();
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public String getKey() {
        return key;
    }

    public long getLastUsage() {
        return this.lastUsage;
    }

    public long getUsageCount() {
        return this.usageCount;
    }

    public void evict() {
        this.softReference.clear();
    }

}
