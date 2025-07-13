
import React, { useContext, useEffect, useState } from 'react';
import { FilterContext } from '../contexts/FilterContext';
import { ProductContext } from '../contexts/ProductContext';

const ProductFilter = () => {
    const { filter, setFilter } = useContext(FilterContext);
    const { products } = useContext(ProductContext);
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        const uniqueCats = Array.from(new Set(products.map(p => p.category)));
        setCategories(uniqueCats);
    }, [products]);

    return (
        <div style={{ marginBottom: 10 }}>
            <label htmlFor="categoryFilter">Filter by Category: </label>
            <select id="categoryFilter" value={filter} onChange={e => setFilter(e.target.value)}>
                <option value="All">All</option>
                {categories.map(cat => (
                    <option key={cat} value={cat}>{cat}</option>
                ))}
            </select>
        </div>
    );
};

export default ProductFilter;
