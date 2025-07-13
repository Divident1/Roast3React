
import React, { useContext, useMemo } from 'react';
import { ProductContext } from '../contexts/ProductContext';

const LowStockAlert = () => {
    const { products } = useContext(ProductContext);
    const lowStockProducts = useMemo(() => products.filter(p => p.quantity < 10), [products]);

    if (!lowStockProducts.length) return null;

    return (
        <div style={{ border: '1px solid red', padding: 10, marginBottom: 10 }}>
            <h3>Low Stock Alerts</h3>
            <ul>
                {lowStockProducts.map(p => (
                    <li key={p.id}>{p.name} — Quantity: {p.quantity}</li>
                ))}
            </ul>
        </div>
    );
};

export default LowStockAlert;
