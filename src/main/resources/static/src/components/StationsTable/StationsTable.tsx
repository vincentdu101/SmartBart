import * as React from "react";
import "./StationsTable.scss";
import { IStationsTableProps, IStationsTableState, IStationDetail } from "../../types/StationTypes";
import { Table } from "reactstrap";
 
export default class StationsTable extends React.Component<IStationsTableProps, IStationsTableState> {

    constructor(props: any) {
        super(props);

        this.outputStationRow = this.outputStationRow.bind(this);

        this.state = {
            stations: []
        };
    }

    public componentDidMount(): void {
        this.setState({stations: []});
    }

    public componentWillReceiveProps(nextProps: Readonly<any>): void {
        this.setState({stations: nextProps.stations});
    }

    private outputStationRow(stations: IStationDetail[]): JSX.Element[] {
        return stations.map((row: IStationDetail, index: number) => {
            console.log(row);
            return (
                <tr key={row.abbr + index}>
                    <td key={"abbr" + index}>{row.abbr}</td>
                    <td key={"name" + index}>{row.name}</td>
                    <td key={"address" + index}>{row.address}</td>
                    <td key={"city" + index}>{row.city}</td>
                    <td key={"county" + index}>{row.county}</td>
                    <td key={"state" + index}>{row.state}</td>
                    <td key={"zipcode" + index}>{row.zipcode}</td>
                    <td key={"options" + index}>Options</td>
                </tr>
            );
        });
    }

    public render(): JSX.Element {
        return (
            <section className="stations-table">
                <Table className="table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">Abbr</th>
                            <th scope="col">Name</th>
                            <th scope="col">Address</th>
                            <th scope="col">City</th>
                            <th scope="col">County</th>
                            <th scope="col">State</th>
                            <th scope="col">Zipcode</th>
                            <th scope="col">Options</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.outputStationRow(this.state.stations)}
                    </tbody>
                </Table>
            </section>
        );
    }

}
