package core.models.converters;

import core.models.DTOs.review.ReviewCreationDto;
import core.models.DTOs.review.ReviewDetailsDto;
import core.models.DTOs.review.ReviewListDto;
import core.models.DTOs.review.ReviewUpdateDto;
import core.models.entities.Review;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewDtoConverter implements DtoConverter<Review, ReviewListDto, ReviewDetailsDto, ReviewCreationDto, ReviewUpdateDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public ReviewDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public Review fromCreation(ReviewCreationDto reviewCreationDto) {
        Review review = modelMapper.map(reviewCreationDto, Review.class);
        review.setId(null);
        return review;
    }

    @Override
    public Review fromUpdate(ReviewUpdateDto reviewUpdateDto) {
        return modelMapper.map(reviewUpdateDto, Review.class);
    }

    @Override
    public ReviewListDto toList(Review review) {
        return modelMapper.map(review, ReviewListDto.class);
    }

    @Override
    public ReviewDetailsDto toDetails(Review review) {
        return modelMapper.map(review, ReviewDetailsDto.class);
    }
}
