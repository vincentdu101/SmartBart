import * as React from "react";
import "./PlannerSection.scss";
import { IPlannerSectionState, IPlannerSectionProps } from "../../../types/PlannerTypes";
import PlannerTable from "../../PlannerTable/PlannerTable";
import DropdownInfo from "../../Dropdown/DropdownInfo";

export default class PlannerSection extends React.Component<{}, IPlannerSectionState> {

    constructor(props: IPlannerSectionProps) {
        super(props);

        this.state = {
            stations: []
        };
    }

    public componentDidMount(): void {

    }

    public render(): JSX.Element {
        return (
            <section className="planner-section">
                <DropdownInfo stations={this.state.stations} />
                <PlannerTable />
            </section>
        );
    }

}