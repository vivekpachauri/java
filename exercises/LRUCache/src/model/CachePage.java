package model;

public class CachePage {
    private final int pageId;
    private final byte[] content;
    public static final int PAGE_SIZE = 4096;

    public CachePage(int pageId, byte[] content) {
        this.pageId = pageId;
        if (content.length != PAGE_SIZE) {
            throw new IllegalArgumentException("Content must be of size " + PAGE_SIZE);
        }
        this.content = content;
    }

    public int getPageId() {
        return pageId;
    }

    public byte[] getContent() {
        return content;
    }
}
