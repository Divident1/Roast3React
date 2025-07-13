
import React from 'react';
import '@testing-library/jest-dom';
import { render, screen, fireEvent } from '@testing-library/react';
import { FilterProvider } from '../contexts/FilterContext';
import { ProductProvider } from '../contexts/ProductContext';
import ProductFilter from '../components/ProductFilter';

test('renders category filter', () => {
    render(
        <ProductProvider>
            <FilterProvider>
                <ProductFilter />
            </FilterProvider>
        </ProductProvider>
    );

    expect(screen.getByLabelText(/Filter by Category/i)).toBeInTheDocument();
});

test('changes the filter value', () => {
    render(
        <ProductProvider>
            <FilterProvider>
                <ProductFilter />
            </FilterProvider>
        </ProductProvider>
    );

    fireEvent.change(screen.getByLabelText(/Filter by Category/i), { target: { value: 'Category 2' } });
    expect(screen.getByLabelText(/Filter by Category/i).value).toBe('Category 2');
});
