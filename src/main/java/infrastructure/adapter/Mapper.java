package infrastructure.adapter;

public interface Mapper <T, V> {
    V toDto(T entity);
    T toEntity(V dto);


}
