package com.mkvbs.recipe_management_service.mapper;

/**
 * @param <F> defines from which type the object will be mapped
 * @param <T> defines to which object will be mapped
 */
public interface Mapper<F, T> {

    T map(F from);
}
