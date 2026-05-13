import React from 'react';

const ButtonBlackWhite = ({
                              children,
                              variant = 'dark',
                              className = '',
                              onClick,
                              ...props
                          }) => {
    // Imposta la classe Bootstrap in base alla variante (nero pieno o contorno nero su sfondo bianco)
    const buttonClass = variant === 'dark' ? 'btn-dark' : 'btn-outline-dark';

    return (
        <button
            className={`btn ${buttonClass} ${className}`}
            onClick={onClick}
            {...props}
        >
            {children}
        </button>
    );
};

export default ButtonBlackWhite;