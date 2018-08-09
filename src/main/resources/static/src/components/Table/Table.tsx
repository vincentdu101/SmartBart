import * as React from "react";
import * as ConfigService from "../../services/Config/ConfigService";
import "./Table.scss";

class Table extends React.Component {

    public render() {
        return (
            <div>Test</div>
        )
    }

    public componentDidMount() {
        fetch(ConfigService.staticFilteredEstimates).then(results => {
            return results.json();
        }).then(data => {
            //  
        });
    }

}

export default Table;