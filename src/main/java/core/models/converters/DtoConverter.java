package core.models.converters;

public interface DtoConverter<TEntity, TListDto, TDetailsDto, TCreationDto, TUpdateDto> {
    TEntity fromCreation(TCreationDto creationDto);
    TEntity fromUpdate(TUpdateDto updateDto);
    TListDto toList(TEntity entity);
    TDetailsDto toDetails(TEntity entity);

}
