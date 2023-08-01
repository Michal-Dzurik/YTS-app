package sk.dzurikm.yts.models;

public class Torrent {
    private String url,quality,type,size;
    private int seeds,peers;

    public Torrent(String url, String quality, String type, String size, int seeds, int peers) {
        this.url = url;
        this.quality = quality;
        this.type = type;
        this.size = size;
        this.seeds = seeds;
        this.peers = peers;
    }

    public Torrent() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSeeds() {
        return seeds;
    }

    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }

    public int getPeers() {
        return peers;
    }

    public void setPeers(int peers) {
        this.peers = peers;
    }

    @Override
    public String toString() {
        return "Torrent{" +
                "url='" + url + '\'' +
                ", quality='" + quality + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", seeds=" + seeds +
                ", peers=" + peers +
                '}';
    }
}
