package br.com.engdb.services.devportal.repositories.predicate;

import com.querydsl.core.BooleanBuilder;

public abstract class BasePredicateBuilder
{
    private BooleanBuilder m_builder = new BooleanBuilder();

    public BooleanBuilder r()
    {
        return m_builder;
    }

}
