import React, { useState } from "react";
import { FaAngleDown } from "react-icons/fa";
import "./FilterItem.scss";

const Item = ({ isFocused, onClick }) => {
    return (
        <div
            className={`filter--main__item ${isFocused ? "focus" : ""}`}
            onClick={onClick}
        >
            <h3>alskdjlfkjasdlf</h3>
        </div>
    );
};

const FilterItem = () => {
    const [expanded, setExpanded] = useState(false);
    const [focusedIndex, setFocusedIndex] = useState(null);

    const handleExpandToggle = () => {
        setExpanded(!expanded);
    };

    const handleItemClick = (index) => {
        let tmpIndex = index === focusedIndex ? null : index;
        setFocusedIndex(tmpIndex);
    };

    return (
        <div className={`filter`}>
            <div className="filter--head" onClick={handleExpandToggle}>
                <h3>Vehicles Category</h3>
                <FaAngleDown
                    className={`arrow-icon ${expanded ? "rotate" : ""}`}
                />
            </div>
            <div className={`filter--main ${expanded ? "expanded" : ""}`}>
                {[0, 1, 2, 3].map((i) => (
                    <Item
                        key={i}
                        isFocused={focusedIndex === i}
                        onClick={() => handleItemClick(i)}
                    />
                ))}
            </div>
        </div>
    );
};

export default FilterItem;
