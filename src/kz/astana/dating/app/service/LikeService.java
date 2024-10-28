package kz.astana.dating.app.service;

public class LikeService {
    private static final LikeService INSTANCE = new LikeService();

    private LikeService() {

    }

    public static LikeService getInstance() {
        return INSTANCE;
    }

    public long getLikesbyId(long id) {
        return 10 + id;
    }
}
