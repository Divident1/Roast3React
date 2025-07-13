
import React from 'react';
import '@testing-library/jest-dom';
import { render, screen, fireEvent } from '@testing-library/react';
import { ProductProvider } from '../contexts/ProductContext';
import { FilterProvider } from '../contexts/FilterContext';
import ProductList from '../components/ProductList';

test('renders the product list', () => {
    render(
        <ProductProvider>
            <FilterProvider>
                <ProductList />
            </FilterProvider>
        </ProductProvider>
    );

    expect(screen.getByText(/Product List/i)).toBeInTheDocument();
});

test('filters products by category', () => {
    render(
        <ProductProvider>
            <FilterProvider>
                <ProductList />
            </FilterProvider>
        </ProductProvider>
    );

    fireEvent.change(screen.getByLabelText(/Filter by Category/i), { target: { value: 'Category 1' } });
    const productAElements = screen.getAllByText(/Product A/i);
    expect(productAElements.length).toBeGreaterThan(0);
    const productCElements = screen.getAllByText(/Product C/i);
    expect(productCElements.length).toBeGreaterThan(0);
});

test('shows no products found message for empty category', () => {
    render(
        <ProductProvider>
            <FilterProvider>
                <ProductList />
            </FilterProvider>
        </ProductProvider>
    );

    fireEvent.change(screen.getByLabelText(/Filter by Category/i), { target: { value: 'Category 4' } });
    expect(screen.getByText(/No products found./i)).toBeInTheDocument();
});
