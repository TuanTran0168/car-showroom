import React, { useState } from "react";
import { FaAngleDown } from "react-icons/fa";
import { CiCircleCheck } from "react-icons/ci";

import { listModels } from "../../common/data/mockData";
import "./FilterItem.scss";

const Item = ({ props, isFocused, onClick }) => {
    return (
        <div
            className={`filter--main__item ${isFocused ? "focus" : ""}`}
            onClick={onClick}
        >
            <h3>{props.name}</h3>
            <CiCircleCheck
                className={`${isFocused ? "display" : "hide"}`}
                fontSize={18}
            />
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
                {listModels.map((v) => (
                    <Item
                        key={v.id}
                        isFocused={focusedIndex === v.id}
                        props={v}
                        onClick={() => handleItemClick(v.id)}
                    />
                ))}
            </div>
        </div>
    );
};

export default FilterItem;
