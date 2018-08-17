import * as React from "react";
import * as ConfigService from "../../services/Config/ConfigService";
import "./PlannerTable.scss";

import { Table } from "reactstrap";

interface IState {
    plans: any;
}

class PlannerTable extends React.Component<{}, IState> {

    constructor(props: any) {
        super(props);
        this.state = {
            plans: {}
        };
    }

    public componentDidMount() {
        this.setState({plans: {}});
        fetch(ConfigService.staticFilteredEstimates + "?orig=24th&dest=rock").then(results => {
            return results.json();
        }).then(data => {
            console.log(data);
            this.setState({plans: data});
        });
    }

    public render() {
        return (
            <Table>
                <thead>
                    <tr>
                        <td>Origin</td>
                        <td>Destination</td>
                        <td>Origin</td>
                        <td>Origin</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{this.state.plans.origin}</td>
                        <td>{this.state.plans.destination}</td>
                    </tr>
                </tbody>
            </Table>
        );
    }

}

export default PlannerTable;