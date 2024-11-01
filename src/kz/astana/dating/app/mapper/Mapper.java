package kz.astana.dating.app.mapper;

public interface Mapper<From, To> {
    To map(From from);
    To map(From from, To to);
}
