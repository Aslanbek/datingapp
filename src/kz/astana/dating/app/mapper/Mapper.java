package kz.astana.dating.app.mapper;

public interface Mapper<From, To> {
    To map(From obj);
}
